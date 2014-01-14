/* This file was generated by SableCC (http://www.sablecc.org/). */

package pascal.node;

import pascal.analysis.*;

@SuppressWarnings("nls")
public final class AOpenIfOpenStatement extends POpenStatement
{
    private POpenIfStatement _openIfStatement_;

    public AOpenIfOpenStatement()
    {
        // Constructor
    }

    public AOpenIfOpenStatement(
        @SuppressWarnings("hiding") POpenIfStatement _openIfStatement_)
    {
        // Constructor
        setOpenIfStatement(_openIfStatement_);

    }

    @Override
    public Object clone()
    {
        return new AOpenIfOpenStatement(
            cloneNode(this._openIfStatement_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOpenIfOpenStatement(this);
    }

    public POpenIfStatement getOpenIfStatement()
    {
        return this._openIfStatement_;
    }

    public void setOpenIfStatement(POpenIfStatement node)
    {
        if(this._openIfStatement_ != null)
        {
            this._openIfStatement_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._openIfStatement_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._openIfStatement_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._openIfStatement_ == child)
        {
            this._openIfStatement_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._openIfStatement_ == oldChild)
        {
            setOpenIfStatement((POpenIfStatement) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}