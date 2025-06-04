package net.wind_weaponry.entity.client;

import net.wind_weaponry.WindChargedWeaponry;
import net.wind_weaponry.entity.custom.WindGolemEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class WindGolemModel extends DefaultedEntityGeoModel<WindGolemEntity> {
    public WindGolemModel() {
        super(WindChargedWeaponry.id("wind_golem"), true);
    }
}
