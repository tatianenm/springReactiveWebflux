package com.reactive.webflux.linhadeonibus.events;

import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinhaDeOnibusEvents {

    private LinhaDeOnibusDTO linhaDeOnibusDTO;

    private Date data;
 }
