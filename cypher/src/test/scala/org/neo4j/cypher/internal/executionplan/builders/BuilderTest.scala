/**
 * Copyright (c) 2002-2012 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.executionplan.builders

import org.scalatest.Assertions
import org.neo4j.cypher.internal.symbols.{SymbolTable, RelationshipType, NodeType, Identifier}
import collection.mutable.{Map => MutableMap}
import org.neo4j.cypher.internal.executionplan.{ExecutionPlanInProgress, PartiallySolvedQuery}
import org.neo4j.cypher.internal.pipes.{Pipe, NullPipe, FakePipe}

abstract class BuilderTest extends Assertions {
  def createPipe(nodes: Seq[String] = Seq(), relationships: Seq[String] = Seq()) = {
    val nodeIdentifiers = nodes.map(x => Identifier(x, NodeType()))
    val relIdentifiers = relationships.map(x => Identifier(x, RelationshipType()))

    new FakePipe(Seq(MutableMap()), new SymbolTable(nodeIdentifiers ++ relIdentifiers: _*))
  }

  def plan(q: PartiallySolvedQuery):ExecutionPlanInProgress = plan(new NullPipe, q)

  def plan(p: Pipe, q: PartiallySolvedQuery):ExecutionPlanInProgress = ExecutionPlanInProgress(q, p)
}