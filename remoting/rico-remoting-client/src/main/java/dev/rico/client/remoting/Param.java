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
package dev.rico.client.remoting;

import dev.rico.internal.core.Assert;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.MAINTAINED;

import java.util.Objects;

/**
 * The class defines a param that can be used as a action param when calling a remoting action on the server side
 * controller. Each param is defined by a name and a value. The name must be unique for a specific action.
 * See {@link ControllerProxy#invoke(String, Param...)} for more details.
 */
@API(since = "0.x", status = MAINTAINED)
public class Param {

    private final String name;

    private final Object value;

    /**
     * Default constructor
     * @param name name of the param
     * @param value value of the param
     *
     * @deprecated Please of {@link #of(String, Object)}
     */
    @Deprecated
    public Param(final String name, final Object value) {
        this.name = Assert.requireNonBlank(name, "name");
        this.value = value;
    }

    /**
     * Returns the param name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the param value
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Param)) return false;

        final Param param = (Param) o;

        if (!name.equals(param.name)) return false;
        return Objects.equals(value, param.value);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    /**
     * Creates a parameter
     * @param name name of the parameter
     * @param value value  of the parameter
     * @return the parameter
     */
    public static Param of(final String name, final Object value) {
        return new Param(name, value);
    }
}
