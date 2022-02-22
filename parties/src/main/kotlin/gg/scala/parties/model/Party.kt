package gg.scala.parties.model

import gg.scala.parties.receiver.PartyReceiverHandler
import gg.scala.parties.service.PartyService
import gg.scala.parties.stream.PartyMessageStream
import net.md_5.bungee.api.chat.BaseComponent
import java.util.*

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
data class Party(
    val uniqueId: UUID = UUID.randomUUID(),
    var leader: PartyMember
)
{
    val members = mutableMapOf<UUID, PartyMember>()
    val settings = mutableMapOf<PartySetting, Boolean>()

    fun sendMessage(message: BaseComponent)
    {
        PartyMessageStream.pushToStream(this, message)
    }

    fun saveAndUpdateParty()
    {
        PartyReceiverHandler.subscriber.publish(this@Party)
        PartyService.service.storeAsync(this.uniqueId, this)
    }
}