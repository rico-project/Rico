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
package dev.rico.internal.remoting.validation;

import dev.rico.remoting.Property;
import org.apiguardian.api.API;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.annotation.Annotation;

import static org.apiguardian.api.API.Status.INTERNAL;

/**
 * Abstract class for validation of {@link Property}
 * @param <T> defines the annotation of the constraints
 * @param <U> the data type that can be validated by this class
 */
@SuppressWarnings("SingleStatementInBlock")
@API(since = "0.19.0", status = INTERNAL)
public abstract class AbstractPropertyValidator<T extends Annotation, U> implements ConstraintValidator<T, Property> {

    private final Class<U> supportedValueClass;

    /**
     * Constructor
     * @param supportedValueClass the data type that can be validated by this class
     */
    public AbstractPropertyValidator(final Class<U> supportedValueClass) {
        this.supportedValueClass = supportedValueClass;
    }

    @Override
    public void initialize(final T constraintAnnotation) {
    }

    /**
     * Checks if the given value is valid
     * @param value the value, guaranteed to be a non-null
     * @param context the context
     * @return true if the value is valid
     */
    protected abstract boolean checkValid(U value,
                           ConstraintValidatorContext context);

    /**
     * Returns true if null should be valid
     * @return true if null should be valid
     */
    protected boolean onNullValue() {
        return true;
    }

    @SuppressWarnings({"SingleStatementInBlock", "unchecked"})
    @Override
    public boolean isValid(final Property property,
                           final ConstraintValidatorContext context) {
        /*
         * TODO: onNullValue should not even be considered when property type is unsupported,
         * e.g. when we are validating Max constraint on Date we should throw runtime exception regardless
         * whether it was null or not.
         * Type check comes first, then onNullValue ***with*** data class as argument.
         */
        if (property == null) {
            return onNullValue();
        }

        final Object value = property.get();

        if(value == null) {
            return onNullValue();
        }
        if(supportedValueClass.isAssignableFrom(value.getClass()) ||
                supportedValueClass.equals(Boolean.class) && value.getClass().equals(Boolean.TYPE)) {
            return checkValid((U) value, context);
        }


        throw new ValidationException("Property contains value of type " + value.getClass() + " instead of " + supportedValueClass);
    }

}

