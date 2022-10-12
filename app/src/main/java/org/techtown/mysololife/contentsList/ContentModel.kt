package org.techtown.mysololife.contentsList
//데이터 클래스
data class ContentModel (
    //제목
    var title : String = "",
    //이미지 주소
    var imageUrl : String = "",
    //이동할 url
    var webUrl : String = ""
)