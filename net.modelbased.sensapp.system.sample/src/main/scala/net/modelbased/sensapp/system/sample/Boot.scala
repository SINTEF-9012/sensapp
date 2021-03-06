/**
 * This file is part of SensApp [ http://sensapp.modelbased.net ]
 *
 * Copyright (C) 2012-  SINTEF ICT
 * Contact: SINTEF ICT <nicolas.ferry@sintef.no>
 *
 * Module: net.modelbased.sensapp.system.sample
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
package net.modelbased.sensapp.system.sample

import akka.actor.{Props, ActorSystem}
import cc.spray._

import net.modelbased.sensapp.service.sample.{Service => SampleService}
import net.modelbased.sensapp.library.system._

class Boot(override val system: ActorSystem) extends System {
  
  trait iod { 
    lazy val partners = new Monolith { implicit val actorSystem = system }
    implicit def actorSystem = system 
  }
  
  def services: List[Service] = List(new SampleService with iod {} )
} 





