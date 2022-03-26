package com.dicoding.githubusersappapi.data.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(

	@field:SerializedName("avatar_url")
	val avatarUrl: String?,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("login")
	val login: String?,

	@field:SerializedName("html_url")
	val htmlUrl: String?
)
{
	override fun equals(other: Any?): Boolean {
		if (javaClass != other?.javaClass) {
			return false
		}

		other as ItemResponse

		when {
			id != other.id -> {
				return false
			}

			login != other.login -> {
				return false
			}

			htmlUrl != other.htmlUrl -> {
				return false
			}

			avatarUrl != other.avatarUrl -> {
				return false
			}
		}

		return super.equals(other)
	}

	override fun hashCode(): Int {
		var result = avatarUrl.hashCode()
		result = 31 * result + id
		result = 31 * result + login.hashCode()
		result = 31 * result + htmlUrl.hashCode()
		return result
	}
}
