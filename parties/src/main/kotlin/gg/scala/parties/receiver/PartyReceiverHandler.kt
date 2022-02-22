package gg.scala.parties.receiver

import gg.scala.parties.model.Party
import gg.scala.parties.service.PartyService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
object PartyReceiverHandler
{
    val subscriber = DataHandler.createPubSubType<Party>(DataStoreType.REDIS, "party-update") {
        PartyService.reloadPartyByUniqueId(it.uniqueId)
    }
}
