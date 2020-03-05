package com.example.simplenotes;

import java.io.Serializable;

public interface StringListener extends Serializable {

    void updateListText(String text, int index);
}
