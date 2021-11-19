package com.cod.controller;

import com.cod.dto.comment.createcomment.CreateCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentInput;
import com.cod.dto.comment.selectcomment.SelectCommentOutput;
import com.cod.dto.comment.updatecomment.UpdateCommentInput;
import com.cod.dto.notice.selectnotice.SelectNoticeInput;
import com.cod.dto.notice.selectnotice.SelectNoticeOutput;
import com.cod.dto.notice.updatenotice.UpdateNotionInput;
import com.cod.response.PageResponse;
import com.cod.response.Response;
import com.cod.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 알림 조회 API
     * [GET] /api/notices?page=&size=
     * @return ResponseEntity<PageResponse<SelectNoticeOutput>>
     */
    // Params
    @GetMapping
    public ResponseEntity<PageResponse<SelectNoticeOutput>> getNoticeList(SelectNoticeInput selectNoticeInput) {
        log.info("[GET] /api/notices?page=&size=");
        return noticeService.selectNoticeList(selectNoticeInput);
    }

    /**
     * 알림 확인 상태 수정 API
     * [PATCH] /api/notices/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Object>> updateNotice(@PathVariable("id") int id, @RequestBody UpdateNotionInput updateNotionInput) {
        log.info("[PATCH] /api/notices/" + id);
        return noticeService.updateNotice(updateNotionInput, id);
    }

    /**
     * 알람 삭제 API
     * [DELETE] /api/notices/{id}
     * @return ResponseEntity<Response<Object>>
     */
    // Path-Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> deleteNotice(@PathVariable("id") int id) {
        log.info("[DELETE] /api/notices/" + id);
        return noticeService.deleteNotice(id);
    }


    /**
     * 알림 조회 API
     * [GET] /api/notices?page=&size=
     * @return ResponseEntity<PageResponse<SelectNoticeOutput>>
     */
    // Params
    @GetMapping("/check")
    public ResponseEntity<Response<Boolean>> checkNewNotice() {
        log.info("[GET] /api/notices/check");
        return noticeService.checkNewNotice();
    }

}

