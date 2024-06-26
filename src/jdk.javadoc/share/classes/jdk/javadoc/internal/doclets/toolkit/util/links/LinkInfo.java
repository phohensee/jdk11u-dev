/*
 * Copyright (c) 2003, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.javadoc.internal.doclets.toolkit.util.links;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * Encapsulates information about a link.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Jamie Ho
 */
public abstract class LinkInfo {

    /**
     * The class we want to link to.  Null if we are not linking
     * to a class.
     */
    public TypeElement typeElement;

    /**
     * The executable element we want to link to.  Null if we are not linking
     * to an executable element.
     */
    public ExecutableElement executableElement;

    /**
     * The Type we want to link to.  Null if we are not linking to a type.
     */
    public TypeMirror type;

    /**
     * True if this is a link to a VarArg.
     */
    public boolean isVarArg = false;

    /**
     * Set this to true to indicate that you are linking to a type parameter.
     */
    public boolean isTypeBound = false;

    /**
     * Whether the document element is in a Java 5 declaration
     * location or not.
     */
    public boolean isJava5DeclarationLocation = true;

    /**
     * The label for the link.
     */
    public Content label;

    /**
     * True if the link should be strong.
     */
    public boolean isStrong = false;

    /**
     * True if we should include the type in the link label.  False otherwise.
     */
    public boolean includeTypeInClassLinkLabel = true;

    /**
     * True if we should include the type as separate link.  False otherwise.
     */
    public boolean includeTypeAsSepLink = false;

    /**
     * True if we should exclude the type bounds for the type parameter.
     */
    public boolean excludeTypeBounds = false;

    /**
     * True if we should print the type parameters, but not link them.
     */
    public boolean excludeTypeParameterLinks = false;

    /**
     * True if we should print the type bounds, but not link them.
     */
    public boolean excludeTypeBoundsLinks = false;

    /**
     * By default, the link can be to the page it's already on.  However,
     * there are cases where we don't want this (e.g. heading of class page).
     */
    public boolean linkToSelf = true;

    /**
     * Return an empty instance of a content object.
     *
     * @return an empty instance of a content object.
     */
    protected abstract Content newContent();

    /**
     * Return true if this link is linkable and false if we can't link to the
     * desired place.
     *
     * @return true if this link is linkable and false if we can't link to the
     * desired place.
     */
    public abstract boolean isLinkable();

    /**
     * Return the label for this class link.
     *
     * @param configuration the current configuration of the doclet.
     * @return the label for this class link.
     */
    public Content getClassLinkLabel(BaseConfiguration configuration) {
        if (label != null && !label.isEmpty()) {
            return label;
        } else if (isLinkable()) {
            Content tlabel = newContent();
            tlabel.add(configuration.utils.getSimpleName(typeElement));
            return tlabel;
        } else {
            Content tlabel = newContent();
            tlabel.add(configuration.getClassName(typeElement));
            return tlabel;
        }
    }

    @Override
    public String toString() {
        return "LinkInfo{" + "typeElement=" + typeElement +
                ", executableElement=" + executableElement +
                ", type=" + type +
                ", isVarArg=" + isVarArg +
                ", isTypeBound=" + isTypeBound +
                ", isJava5DeclarationLocation=" + isJava5DeclarationLocation +
                ", label=" + label +
                ", isStrong=" + isStrong +
                ", includeTypeInClassLinkLabel=" + includeTypeInClassLinkLabel +
                ", includeTypeAsSepLink=" + includeTypeAsSepLink +
                ", excludeTypeBounds=" + excludeTypeBounds +
                ", excludeTypeParameterLinks=" + excludeTypeParameterLinks +
                ", excludeTypeBoundsLinks=" + excludeTypeBoundsLinks +
                ", linkToSelf=" + linkToSelf + '}';
    }
}
