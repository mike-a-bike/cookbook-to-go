/*
 * Copyright 2016 Michael Bieri
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

package ch.zweivelo.ctg.repo.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 * TODO: COMMENT
 *
 * @author <a href="mailto:m.bieri@gmx.net">Michael Bieri</a>
 * @version 0.1
 * @since 08.06.2016
 */

@Embeddable
public class SecurityDetails {

    @Lob
    @NotNull
    private byte[] salt;

    @Lob
    @NotNull
    private byte[] password;

    private boolean twoFactorEnabled;

    private boolean tainted;

    SecurityDetails() {
    }

    public SecurityDetails(final byte[] salt, final byte[] password) {
        this.salt = salt;
        this.password = password;
        twoFactorEnabled = false;
        tainted = false;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(final byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(final byte[] password) {
        this.password = password;
    }

    public boolean isTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(final boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public boolean isTainted() {
        return tainted;
    }

    public void setTainted(final boolean tainted) {
        this.tainted = tainted;
    }
}
