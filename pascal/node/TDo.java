/* This file was generated by SableCC (http://www.sablecc.org/). */

package pascal.node;

import pascal.analysis.*;

@SuppressWarnings("nls")
public final class TDo extends Token
{
    public TDo(String text)
    {
        setText(text);
    }

    public TDo(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TDo(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTDo(this);
    }
}