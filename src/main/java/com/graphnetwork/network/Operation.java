package com.graphnetwork.network;

import com.graphnetwork.network.Dto.KafaMessagedto;

public interface Operation {
    void crudoperation(KafaMessagedto kafaMessagedto);
}
