package wayoftime.bloodmagic.common.data.recipe.builder;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import wayoftime.bloodmagic.recipe.helper.SerializerHelper;
import wayoftime.bloodmagic.common.data.recipe.BloodMagicRecipeBuilder;
import wayoftime.bloodmagic.util.Constants;

public class AlchemyArrayRecipeBuilder extends BloodMagicRecipeBuilder<AlchemyArrayRecipeBuilder>
{
	private final ResourceLocation texture;
	private final Ingredient baseInput;
	private final Ingredient addedInput;
	private final ItemStack output;

	protected AlchemyArrayRecipeBuilder(ResourceLocation texture, Ingredient baseInput, Ingredient addedInput, ItemStack output)
	{
		super(bmSerializer("array"));
		this.texture = texture;
		this.baseInput = baseInput;
		this.addedInput = addedInput;
		this.output = output;
	}

	public static AlchemyArrayRecipeBuilder array(ResourceLocation texture, Ingredient baseInput, Ingredient addedInput, ItemStack output)
	{
		return new AlchemyArrayRecipeBuilder(texture, baseInput, addedInput, output);
	}

	@Override
	protected AlchemyArrayRecipeResult getResult(ResourceLocation id)
	{
		return new AlchemyArrayRecipeResult(id);
	}

	public class AlchemyArrayRecipeResult extends RecipeResult
	{
		protected AlchemyArrayRecipeResult(ResourceLocation id)
		{
			super(id);
		}

		@Override
		public void serialize(@Nonnull JsonObject json)
		{
			json.addProperty(Constants.JSON.TEXTURE, texture.toString());
//			JSONUtils.getString(json, );
			json.add(Constants.JSON.BASEINPUT, baseInput.serialize());
			json.add(Constants.JSON.ADDEDINPUT, addedInput.serialize());
			json.add(Constants.JSON.OUTPUT, SerializerHelper.serializeItemStack(output));
		}
	}
}
