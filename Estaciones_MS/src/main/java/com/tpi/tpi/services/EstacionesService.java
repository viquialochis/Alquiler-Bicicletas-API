package com.tpi.tpi.services;

import com.tpi.tpi.model.dto.RequestEstacionDTO;
import com.tpi.tpi.model.dto.ResponseEstacionDTO;


public interface EstacionesService extends Service<RequestEstacionDTO, ResponseEstacionDTO, Integer> {

    ResponseEstacionDTO getEstacionMasCercana(Double latitud, Double longitud);

}
