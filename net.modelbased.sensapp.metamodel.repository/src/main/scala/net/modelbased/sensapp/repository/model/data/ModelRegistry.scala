/**
 * This file is part of SensApp [ http://sensapp.modelbased.net ]
 *
 * Copyright (C) 2011-  SINTEF ICT
 * Contact: Sebastien Mosser <sebastien.mosser@sintef.no>
 *
 * Module: net.modelbased.sensapp.metamodel.repository
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
package net.modelbased.sensapp.repository.model.data

import net.modelbased.sensapp.datastore._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObjectBuilder

class ModelRegistry extends DataStore[Model] {

  override val databaseName = "sensapp_db"
  override val collectionName = "models.registry" 
    
  override def identify(m: Model) = ("name", m.name)
  
  override def deserialize(dbObj: DBObject): Model = {
    Model(dbObj.as[String]("name"), dbObj.as[String]("content"))
  }
 
  override def serialize(obj: Model): DBObject = {
    val builder = MongoDBObject.newBuilder
    builder += ("name" -> obj.name)
    builder += ("content" -> obj.content)
    builder.result
  }
    
}