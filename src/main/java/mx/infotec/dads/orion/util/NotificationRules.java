/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.orion.util;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

public class NotificationRules {

    private NotificationRules() {
    }

    public static SeverityMessage levelOfSeverity(int co3) {
        if (co3 < 50) {
            return new SeverityMessage("alert alert-success", "text-success", "Good", "The level is Good");
        } else if ((51 <= co3) && (co3 <= 100)) {
            return new SeverityMessage("alert alert-warning", "text-warning", "Regular", "It is Acceptable");
        } else if ((101 <= co3) && (co3 <= 150)) {
            return new SeverityMessage("alert alert-orange", "text-orange", "Bad", "The level is bad");
        } else if((151 <= co3) && (co3 <= 200)){
            return new SeverityMessage("alert alert-danger", "text-danger", "Very Bad", "The level is very bad");
        }else{
            return new SeverityMessage("alert alert-primary", "text-primary", "Extremely Bad", "The level is Extremely bad");
        }
    }
}
