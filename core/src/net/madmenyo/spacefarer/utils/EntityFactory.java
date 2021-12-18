package net.madmenyo.spacefarer.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.madmenyo.spacefarer.components.BoundingCircleComponent;
import net.madmenyo.spacefarer.components.ControlComponent;
import net.madmenyo.spacefarer.components.DebugComponent;
import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.components.ShipComponent;
import net.madmenyo.spacefarer.components.SlotComponent;
import net.madmenyo.spacefarer.components.TextureComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.components.VelocityComponent;
import net.madmenyo.spacefarer.components.WeaponComponent;


public class EntityFactory {

    public static Entity CreatePlayer(PooledEngine engine){
        Entity player = engine.createEntity();

        ControlComponent control = ControlComponent.create(engine);
        player.add(control);

        return player;
    }

    public static Entity CreateArmedShip(PooledEngine engine, Entity pilot){
        Entity shipEntity = CreateShip(0, 0, 0, pilot, engine);


        Entity slot = CreateSlot(40, -10, 0, shipEntity, engine);
        engine.addEntity(slot);

        ShipComponent shipComponent = Mapper.ShipMapper.get(shipEntity);
        shipComponent.slots.add(slot);

        Entity cannon = CreateCannon(engine, slot);
        SlotComponent slotComponent = Mapper.SlotMapper.get(slot);
        slotComponent.weapon = cannon;

        slot = CreateSlot(-40, -10, 0, shipEntity, engine);
        shipComponent.slots.add(slot);

        engine.addEntity(slot);

        cannon = CreateCannon(engine, slot);
        slotComponent = Mapper.SlotMapper.get(slot);
        slotComponent.weapon = cannon;


        return shipEntity;
    }

    private static Entity CreateCannon(PooledEngine engine, Entity slot) {
        Entity entity = engine.createEntity();

        WeaponComponent weaponComponent = WeaponComponent.create(engine);
        entity.add(weaponComponent);

        ParentComponent parentComponent = ParentComponent.create(engine);
        parentComponent.entity = slot;
        entity.add(parentComponent);

        TransformComponent transformComponent = TransformComponent.create(engine);
        //transformComponent.scale.set(5, 5);
        transformComponent.position.z = -200;
        transformComponent.position.y = 10;
        transformComponent.originOffset.y =  -8;
        entity.add(transformComponent);

        TextureComponent textureComponent = TextureComponent.create(engine);
        textureComponent.region = new TextureRegion(new Texture("tmp/cannon.png"));
        entity.add(textureComponent);

        /*
        BoundingCircleComponent circleComponent = BoundingCircleComponent.create(engine);
        circleComponent.circle.radius = 5;
        entity.add(circleComponent);
         */

        DebugComponent debugComponent = DebugComponent.create(engine);
        debugComponent.drawOrigin = true;
        entity.add(debugComponent);

        engine.addEntity(entity);
        return entity;
    }

    public static Entity CreateShip(PooledEngine engine, Entity pilot){

        return CreateShip(0,0,0, pilot, engine);
    }

    public static Entity CreateShip(float x, float y, float rotation, Entity pilot, PooledEngine engine){

        Entity shipEntity = engine.createEntity();

        ShipComponent ship = ShipComponent.create(engine);
        ship.pilot = pilot;
        shipEntity.add(ship);

        TextureComponent texture = TextureComponent.create(engine);
        texture.region = new TextureRegion(new Texture("tmp/Kai.png"));
        shipEntity.add(texture);

        TransformComponent transform = TransformComponent.create(engine);
        transform.position.set(x, y, 0);
        transform.rotation = rotation;
        shipEntity.add(transform);

        VelocityComponent velocity = VelocityComponent.create(engine);
        shipEntity.add(velocity);

        BoundingCircleComponent circle = BoundingCircleComponent.create(engine);
        circle.circle.radius = 80;
        shipEntity.add(circle);

        return shipEntity;
    }

    private static Entity CreateSlot(float x, float y, float direction, Entity ship, PooledEngine engine){
        Entity slot = engine.createEntity();

        ParentComponent parentComponent = ParentComponent.create(engine);
        parentComponent.entity = ship;
        slot.add(parentComponent);

        TransformComponent transformComponent = TransformComponent.create(engine);
        // Draw weapons always above ship
        transformComponent.position.set(x, y, 100);
        transformComponent.rotation = direction;
        slot.add(transformComponent);

        SlotComponent slotComponent = SlotComponent.create(engine);
        slotComponent.direction = direction;
        slot.add(slotComponent);

        /*
        BoundingCircleComponent circleComponent = BoundingCircleComponent.create(engine);
        circleComponent.circle.radius = 10;
        slot.add(circleComponent);
         */

        return slot;
    }

    private static Entity CreateWeapon(PooledEngine engine){
        Entity weapon = engine.createEntity();

        TextureComponent textureComponent = TextureComponent.create(engine);
        textureComponent.region = new TextureRegion(new Texture("tmp/cannon.png"));

        TransformComponent transformComponent = TransformComponent.create(engine);
        weapon.add(transformComponent);

        ParentComponent parentComponent = ParentComponent.create(engine);
        weapon.add(parentComponent);

        WeaponComponent weaponComponent = WeaponComponent.create(engine);
        weapon.add(weaponComponent);

        return weapon;
    }
}
