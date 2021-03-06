/*
 * Copyright 2018-2019 Karakun AG.
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.rico.internal.server.remoting.controller.repository;

import dev.rico.internal.server.remoting.controller.TestBean;
import dev.rico.remoting.server.RemotingAction;
import dev.rico.remoting.server.RemotingController;
import dev.rico.remoting.server.RemotingModel;
import dev.rico.remoting.server.Param;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RemotingController
public class TestController {


    @RemotingModel
    private TestBean testBean;

    @PostConstruct
    public void postConstruct() {
    }

    @PreDestroy
    public void preDestroy() {
    }

    @RemotingAction
    public void action(@Param String test) {
    }
}
