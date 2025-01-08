package com.example.demokmp

class HandleUserRequest {

    fun request(userName: String, repo: String, path: String) {
        println("Submitted name: ${userName}, repo ${repo}, path: ${path}")
        val url: String = getUrl(userName, repo, path)
        getRawFile(url)
    }

    private fun getRawFile(url: String) {
        TODO("Not yet implemented")
    }

    private fun getUrl(userName: String, repo: String, path: String): String {
        val baseURL: String = "https://raw.githubusercontent.com"
        val result: String = "$baseURL/$userName/$repo/refs/heads/$path"
        println("result from getUrl= ${result}")
        return result
    }
}