package com.cod.service;

import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.dto.notice.createnotice.CreateNoticeInput;
import com.cod.dto.notice.selectnotice.SelectNoticeInput;
import com.cod.dto.notice.selectnotice.SelectNoticeOutput;
import com.cod.dto.notice.updatenotice.UpdateNotionInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import org.springframework.http.ResponseEntity;

public interface NoticeService {
    ResponseEntity<Response<Object>> createNotice(CreateNoticeInput createNoticeInput);
    ResponseEntity<PageResponse<SelectNoticeOutput>> selectNoticeList(SelectNoticeInput selectNoticeInput);
    ResponseEntity<Response<Object>> updateNotice(UpdateNotionInput updateNotionInput, int noticeId);
    ResponseEntity<Response<Object>> deleteNotice(int noticeId);
    ResponseEntity<Response<Boolean>> checkNewNotice();
}
