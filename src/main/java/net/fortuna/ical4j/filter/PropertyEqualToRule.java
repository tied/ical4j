/**
 * Copyright (c) 2012, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.filter;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.util.List;
import java.util.function.Predicate;

/**
 * $Id$
 *
 * Created on 5/02/2006
 *
 * A rule that matches any component containing the specified property. Note that this rule ignores any parameters
 * matching only on the value of the property.
 * @author Ben Fortuna
 */
public class PropertyEqualToRule<T extends Component> implements Predicate<T> {

    private final String propertyName;

    private final Object value;

    /**
     * Constructs a new instance with the specified property. Ignores any parameters matching only on the value of the
     * property.
     * @param property a property instance to check for
     */
    public PropertyEqualToRule(final Property property) {
        this(property.getName(), property.getValue());
    }

    public PropertyEqualToRule(String propertyName, Object value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean test(final T component) {
        final List<Property> properties = component.getProperties().get(property.getName());
        for (final Property p : properties) {
            if (value.equals(p.getValue())) {
                return true;
            }
        }
        return false;
    }
}