/**
 * ====
 *     This file is part of SensApp [ http://sensapp.modelbased.net ]
 *
 *     Copyright (C) 2011-  SINTEF ICT
 *     Contact: SINTEF ICT <nicolas.ferry@sintef.no>
 *
 *     Module: net.modelbased.sensapp
 *
 *     SensApp is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as
 *     published by the Free Software Foundation, either version 3 of
 *     the License, or (at your option) any later version.
 *
 *     SensApp is distributed in the hope that it will be useful, but
 *     WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General
 *     Public License along with SensApp. If not, see
 *     <http://www.gnu.org/licenses/>.
 * ====
 *
 * This file is part of SensApp [ http://sensapp.modelbased.net ]
 *
 * Copyright (C) 2012-  SINTEF ICT
 * Contact: SINTEF ICT <nicolas.ferry@sintef.no>
 *
 * Module: net.modelbased.sensapp.backyard.echo.ws
 *
 * SensApp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * SensApp is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with SensApp. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package net.modelbased.sensapp.backyard.echo.ws

import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import net.modelbased.sensapp.library.ws.Client.{WsClientFactory, WsClient}
import net.modelbased.sensapp.library.ws.Server.WsServerScala

object WsEchoClient{
  def main(args: Array[String]) {
    var client: WsClient = null
    //client.connect()
    //Thread.sleep(2000)
    val registerToTopic = WsServerScala.registerToTopic

    println(
      """
        |
        | The WebSocket Echo Client is ready
        |
        | connect                       connect this client to the server
        | registerToTopic=<id-topic>    identify this client to the notification server
        | disconnect                    disconnect the client
        | quit                          kill the app
        |
        | Please choose your command
        |
      """.stripMargin)

    //client.send(message)
    var quit = false
    while(!quit){
      Thread.sleep(2000)
      val line = readLine()
      if(line == "connect"){
        client = WsClientFactory.makeClient( URI.create( "ws://127.0.0.1:9000" ))
        client.connect()
        println("Connection")
      }
      else if(line.contains(registerToTopic)){
        val message = registerToTopic + line.substring(registerToTopic.length, line.length)
        client.send(message)
        println("Identification message sent")
      }
      else if(line == "disconnect"){
        client.close
        println("Disconnection")
      }
      else if(line == "quit")
        quit = true
      else{
        client.send(line)
      }
    }
  }
}