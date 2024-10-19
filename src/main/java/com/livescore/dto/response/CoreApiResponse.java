package com.livescore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreApiResponse {

    private int code;

    private int statusCode;

    private Object data;
}
