package com.habitrpg.android.habitica.models.invitations

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PartyInvite : RealmObject(), GenericInvitation {

    @PrimaryKey
    override var id: String? = null
    override var name: String? = null
    override var inviter: String? = null
}
