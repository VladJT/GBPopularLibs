package jt.projects.gbpopularlibs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(val login: String) : Parcelable