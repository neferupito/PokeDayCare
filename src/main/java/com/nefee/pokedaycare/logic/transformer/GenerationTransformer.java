package com.nefee.pokedaycare.logic.transformer;

import com.nefee.pokedaycare.data.entity.generation.Generation;

public class GenerationTransformer {

    public static Generation integerToGeneration(Integer intgr) {

        if (intgr == null) {
            return Generation.GEN6;
        }

        Generation result = null;
        switch (intgr) {
            case 1:
                result = Generation.GEN1;
                break;
            case 2:
                result = Generation.GEN2;
                break;
            case 3:
                result = Generation.GEN3;
                break;
            case 4:
                result = Generation.GEN4;
                break;
            case 5:
                result = Generation.GEN5;
                break;
            case 6:
                result = Generation.GEN6;
                break;
        }

        return result;
    }

}
