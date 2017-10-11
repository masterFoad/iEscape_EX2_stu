package gui.utils;

import javafx.scene.control.TextField;

/**
 * A TextField that is limited to integers and has a maximum length of 10
 */
public class NumberTextField extends TextField
{

    private final int limit=10;

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
        verify();
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
        verify();
    }

    private boolean validate(String text)
    {
        return text.matches("[0-9]*");
    }


    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }

    }

}