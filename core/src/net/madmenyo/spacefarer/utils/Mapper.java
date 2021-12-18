package net.madmenyo.spacefarer.utils;

import com.badlogic.ashley.core.ComponentMapper;

import net.madmenyo.spacefarer.components.AiComponent;
import net.madmenyo.spacefarer.components.BoundingCircleComponent;
import net.madmenyo.spacefarer.components.ControlComponent;
import net.madmenyo.spacefarer.components.DebugComponent;
import net.madmenyo.spacefarer.components.FactionComponent;
import net.madmenyo.spacefarer.components.HealthComponent;
import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.components.ShipComponent;
import net.madmenyo.spacefarer.components.SlotComponent;
import net.madmenyo.spacefarer.components.TextureComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.components.VelocityComponent;
import net.madmenyo.spacefarer.components.WeaponComponent;

public class Mapper {
    public static ComponentMapper<TransformComponent> TransformMapper =
            ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<VelocityComponent> VelocityMapper =
            ComponentMapper.getFor(VelocityComponent.class);
    public static ComponentMapper<TextureComponent> TextureMapper =
            ComponentMapper.getFor(TextureComponent.class);
    public static ComponentMapper<ControlComponent> ControlMapper =
            ComponentMapper.getFor(ControlComponent.class);
    public static ComponentMapper<HealthComponent> HealthMapper =
            ComponentMapper.getFor(HealthComponent.class);
    public static ComponentMapper<ParentComponent> ParentMapper =
            ComponentMapper.getFor(ParentComponent.class);
    public static ComponentMapper<ShipComponent> ShipMapper =
            ComponentMapper.getFor(ShipComponent.class);
    public static ComponentMapper<FactionComponent> FactionMapper =
            ComponentMapper.getFor(FactionComponent.class);
    public static ComponentMapper<BoundingCircleComponent> BoundingCircleMapper =
            ComponentMapper.getFor(BoundingCircleComponent.class);
    public static ComponentMapper<SlotComponent> SlotMapper =
            ComponentMapper.getFor(SlotComponent.class);
    public static ComponentMapper<WeaponComponent> WeaponMapper =
            ComponentMapper.getFor(WeaponComponent.class);
    public static ComponentMapper<DebugComponent> DebugMapper =
            ComponentMapper.getFor(DebugComponent.class);
    public static ComponentMapper<AiComponent> AiMapper =
            ComponentMapper.getFor(AiComponent.class);

}
