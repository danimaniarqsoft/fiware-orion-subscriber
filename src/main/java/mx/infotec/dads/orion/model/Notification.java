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
package mx.infotec.dads.orion.model;

import org.springframework.data.annotation.Id;

import mx.infotec.dads.orion.util.NotificationRules;
import mx.infotec.dads.orion.util.SeverityMessage;

/**
 * Temperature of a Room
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Notification {
    @Id
    private String id;
    private String orionId;
    private String type;
    private String latitud;
    private String longitud;
    private int o3;
    private String name;
    private OrionAlert orionAlert;
    private SeverityMessage severityMessage;

    public Notification(OrionAlert orionAlert) {
        this.orionAlert = orionAlert;
        Data data = orionAlert.getData().get(0);
        this.orionId = data.getId();
        this.type = data.getType();
        this.latitud = data.getAdditionalProperties().get("latitud").toString();
        this.longitud = data.getAdditionalProperties().get("longitud").toString();
        this.o3 = Integer.valueOf(data.getAdditionalProperties().get("o3").toString());
        this.name = data.getAdditionalProperties().get("nombre").toString();
        severityMessage = NotificationRules.levelOfSeverity(this.o3);
    }

    public OrionAlert getOrionAlert() {
        return orionAlert;
    }

    public void setOrionAlert(OrionAlert orionAlert) {
        this.orionAlert = orionAlert;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrionId() {
        return orionId;
    }

    public void setOrionId(String orionId) {
        this.orionId = orionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getO3() {
        return o3;
    }

    public void setO3(int o3) {
        this.o3 = o3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeverityMessage getSeverityMessage() {
        return severityMessage;
    }

    public void setSeverityMessage(SeverityMessage severityMessage) {
        this.severityMessage = severityMessage;
    }

}