/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.sparkbindings.shell

import org.apache.mahout.sparkbindings._
import org.apache.log4j.PropertyConfigurator


object Main {

  private var _interp: MahoutSparkILoop = _

  def main(args:Array[String]) {

    // Hack: for some very unclear reason, log4j is not picking up log4j.properties in Spark conf/ even
    // though the latter is added to the classpath. So we force it to pick it.
    PropertyConfigurator.configure(getMahoutHome() + "/conf/log4j.properties")

    System.setProperty("scala.usejavacp", "true")
    _interp = new MahoutSparkILoop()
    // It looks like we need to initialize this too, since some Spark shell initilaization code
    // expects it
    org.apache.spark.repl.Main.interp = _interp
    _interp.process(args)
  }

}