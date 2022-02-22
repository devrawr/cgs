package gg.scala.cgs.common.instance

import gg.scala.cgs.common.instance.game.CgsGameServerInfo
import java.util.*

/**
 * @author GrowlyX
 * @since 11/30/2021
 */
class CgsServerInstance(
    val internalServerId: String,
    val type: CgsServerType,
    var online: Int = 0
)
{
    val identifier: UUID = UUID.randomUUID()
    var gameServerInfo: CgsGameServerInfo? = null
}
