package net.brodino.roleplaysimplevoicechat.effects;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EffectsManager {

	public static StatusEffect NEGATE_SPEECH = new NegateSpeechEffect(StatusEffectCategory.NEUTRAL, 1);
	public static StatusEffect EXTEND_SPEECH = new ExtendSpeechEffect(StatusEffectCategory.NEUTRAL, 1);

	private static void registerEffect(String id, StatusEffect effect) {
		Registry.register(Registry.STATUS_EFFECT, new Identifier(RoleplaySimpleVoicechat.MOD_ID, id), effect);
	}

	public static void initialize() {
		registerEffect("negate_speech", NEGATE_SPEECH);
		registerEffect("amplify_speech", EXTEND_SPEECH);
	}
}
