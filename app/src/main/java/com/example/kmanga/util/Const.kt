package com.example.kmanga.util

class Const {
    companion object {
        /** Milliseconds used for UI animations */
        const val ANIMATION_FAST_MILLIS = 50L
        const val ANIMATION_SLOW_MILLIS = 100L

        const val SHORT_DEBOUNCE_DURATION = 300L

        /** Tool IDs **/
        const val TOOL_SCAN_ID_CARD = 1000
        const val TOOL_SCAN_TO_TEXT = 1001
        const val TOOL_SCAN_QR_CODE = 1002
        const val TOOL_IMPORT_IMAGES_FILES = 1003
        const val TOOL_CONVERT_TO_PDF = 1004
        const val TOOL_CONVERT_TO_WORD = 1005
        const val TOOL_CONVERT_TO_EXCEL = 1006
        const val TOOL_PDF_TO_IMAGES = 1007
        const val TOOL_SIGNATURE = 1008
        const val TOOL_ADD_WATERMARK = 1009
        const val TOOL_MERGE_PDFS = 1010
        const val TOOL_EXTRACT_PDF_PAGES = 1011
        const val TOOL_REORDER_PDF_PAGES = 1012
        const val TOOL_SET_PDF_PASSWORD = 1013

        /** mime type **/
        const val MIME_TYPE_PDF = "application/pdf"
    }
}