//package com.example.kmanga.service.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.sota.viescan.data.model.MyFile
//
//class MyFilePagingSource constructor(
//    private val apiFile: FileAPI,
//    private val downloadLink: Boolean = true,
//) : PagingSource<Int, MyFile>() {
//
//    private val INITIAL_LOAD_SIZE = 1
//
//    override fun getRefreshKey(state: PagingState<Int, MyFile>): Int? {
//
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyFile> {
//        // Start refresh at position 1 if undefined.
//        val page = params.key ?: INITIAL_LOAD_SIZE
//        return try {
//            val response =
//                apiFile.fetchFiles(limit = params.loadSize, page = page, tags = "", downloadLink = downloadLink)
//            val result = response.body()?.data?.map { it.toModel() } ?: listOf()
//            val nextKey = if (result.isEmpty()) {
//                null
//            } else {
//                // initial load size = 3 * NETWORK_PAGE_SIZE
//                // ensure we're not requesting duplicating items, at the 2nd request
//                page + 1
//            }
//            LoadResult.Page(
//                data = result,
//                prevKey = null, // Only paging forward.
//                // assume that if a full page is not loaded, that means the end of the data
//                nextKey = nextKey
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}