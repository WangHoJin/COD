package com.cod.dto.notice.selectnotice;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SelectNoticeInput {
    private int page;
    private int size;
}
