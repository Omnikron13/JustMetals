/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2019

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.core;

/**
 * Created by CovertJaguar on 11/17/2018 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ClientAccessException extends RuntimeException {
    public ClientAccessException() {
        super("This feature is not available on the client thread! It won't work!");
    }
}
