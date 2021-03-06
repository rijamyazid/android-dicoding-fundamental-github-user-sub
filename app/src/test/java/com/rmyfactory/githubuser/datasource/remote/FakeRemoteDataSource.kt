package com.rmyfactory.githubuser.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.rmyfactory.githubuser.datasource.local.LocalSealed
import com.rmyfactory.githubuser.datasource.local.model.UserModel
import com.rmyfactory.githubuser.util.NetworkConstant.CODE_EMPTY

class FakeRemoteDataSource(
    var fakeListUsers: MutableList<UserModel> = mutableListOf(),
    var fakeUserByQueryandUsername: Map<String, List<UserModel>> = mutableMapOf()
) : RemoteDataSource {

    override fun getUsers(): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeListUsers.isEmpty()) {
                LocalSealed.Error(CODE_EMPTY)
            } else {
                LocalSealed.Value(fakeListUsers)
            }
        )
    }

    override fun getUsersByQuery(query: String): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeUserByQueryandUsername.isEmpty()) {
                LocalSealed.Error(CODE_EMPTY)
            } else {
                LocalSealed.Value(fakeUserByQueryandUsername[query]!!)
            }
        )
    }

    override fun getFollowers(username: String): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeUserByQueryandUsername.isEmpty()) {
                LocalSealed.Error(CODE_EMPTY)
            } else {
                LocalSealed.Value(fakeUserByQueryandUsername[username]!!)
            }
        )
    }

    override fun getFollowing(username: String): LiveData<LocalSealed<List<UserModel>>> = liveData {
        emit(
            if (fakeUserByQueryandUsername.isEmpty()) {
                LocalSealed.Error(CODE_EMPTY)
            } else {
                LocalSealed.Value(fakeUserByQueryandUsername[username]!!)
            }
        )
    }


}