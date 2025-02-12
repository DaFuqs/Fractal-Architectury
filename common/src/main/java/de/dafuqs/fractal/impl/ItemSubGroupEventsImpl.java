package de.dafuqs.fractal.impl;

import de.dafuqs.fractal.api.*;
import net.fabricmc.fabric.api.event.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.*;

import java.util.*;

public class ItemSubGroupEventsImpl {
	
	private static final Map<ResourceLocation, Event<ItemSubGroupEvents.ModifyEntries>> ITEM_GROUP_EVENT_MAP = new HashMap<>();

	public static Event<ItemSubGroupEvents.ModifyEntries> getOrCreateModifyEntriesEvent(ResourceLocation identifier) {
		return ITEM_GROUP_EVENT_MAP.computeIfAbsent(identifier, (g -> createModifyEvent()));
	}

	@Nullable
	public static Event<ItemSubGroupEvents.ModifyEntries> getModifyEntriesEvent(ResourceLocation identifier) {
		return ITEM_GROUP_EVENT_MAP.get(identifier);
	}

	private static Event<ItemSubGroupEvents.ModifyEntries> createModifyEvent() {
		return EventFactory.createArrayBacked(ItemSubGroupEvents.ModifyEntries.class, callbacks -> (entries) -> {
			for (ItemSubGroupEvents.ModifyEntries callback : callbacks) {
				callback.modifyEntries(entries);
			}
		});
	}
}
