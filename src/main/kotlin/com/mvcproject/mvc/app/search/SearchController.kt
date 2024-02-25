package com.mvcproject.mvc.app.search

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * 検索関連のリクエストを処理するコントローラー。
 */
@Controller
@RequestMapping("search")
class SearchController {
    /**
     * "/search" エンドポイントへの GET リクエストを処理します。
     *
     * @return レンダリングするビューの名前。"search/search.html" が返されます。
     */
    @RequestMapping(method = [RequestMethod.GET])
    fun getToSearch(): String = "search/search.html"

    /**
     * "/search/{lot_no}" エンドポイントへの GET リクエストを処理します。
     *
     * @return レンダリングするビューの名前。"search/search_info.html" が返されます。
     */
    @RequestMapping(value = ["{lot_no}"], method = [RequestMethod.GET])
    fun getToLotDetails() = "search/search_info.html"
}
