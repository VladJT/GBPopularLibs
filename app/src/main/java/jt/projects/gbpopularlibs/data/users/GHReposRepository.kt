package jt.projects.gbpopularlibs.data.users

//class GHReposRepository(
//    val api: IDataSource,
//    val networkStatus: INetworkStatus,
//    val db: AppDatabase
//) : IUserGHReposRepository {
//
//    override fun getUserGHRepos(user: UserEntity): Single<List<UserGHRepo>> =
//        networkStatus.isOnlineSingle().flatMap { isOnline ->
//            if (isOnline) {
//                api.getUsersApi().getRepos(user.login)
//                    .flatMap { repo ->
//                        Single.fromCallable {
//                            // cacheImpl.saveUsers(users)
//                            repo
//                        }
//                    }
//            }
//        }.subscribeOn(Schedulers.io())
//
//
////            if (isOnline) {
////                user.repos_url?.let { url ->
////                    api.getUsersApi().getRepos(user.login)
////
////                }
////            } ?: Single.error<List<UserGHRepo>>(
////                RuntimeException(
////                    "User has no repos url "
////                )
////            )
////
////        } else
////    {
////        Single.fromCallable {
////            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
////                ?: throw RuntimeException("No such user in cache")
////            db.repositoryDao.findForUser(roomUser.id).map {
////                GithubRepository(it.id, it.name, it.forksCount)
////            }
////        }
////    }
////}.subscribeOn(Schedulers.io())
//
//
//}