/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.codegen;

import org.jetbrains.jet.ConfigurationKind;

/**
 * This test is separate from MultiDeclTestGenerated because we specifically test generated bytecode
 * that expression does not evaluated several time
 */
public class ComponentGenTest extends CodegenTestCase {
    public void testComponent() {
        createEnvironmentWithMockJdkAndIdeaAnnotations(ConfigurationKind.JDK_ONLY);
        blackBoxFile("componentEvaluatesOnlyOnce.kt");

        String asm = generateToText();
        //System.out.println(asm);
        final String pattern = "NEW S\n";
        final int index = asm.indexOf(pattern);
        asm = asm.substring(index + pattern.length());
        assertEquals(-1, asm.indexOf(pattern));
    }
}
