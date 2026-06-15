package dev.brodino.roleplaysimplevoicechat.effects;

import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EffectsManager {

	public static StatusEffect EXTENDED_SPEECH = new ExtendedSpeechEffect(StatusEffectCategory.NEUTRAL, 2941931 /* Sonic boom azure */);
	public static StatusEffect NEGATED_SPEECH = new NegatedSpeechEffect(StatusEffectCategory.NEUTRAL, 1);

	public static void initialize() {
		registerEffect("extended_speech", EXTENDED_SPEECH);
		registerEffect("negated_speech", NEGATED_SPEECH);
	}

	private static void registerEffect(String id, StatusEffect effect) {
		Registry.register(Registry.STATUS_EFFECT, new Identifier(RoleplaySimpleVoicechat.MOD_ID, id), effect);
	}
}
