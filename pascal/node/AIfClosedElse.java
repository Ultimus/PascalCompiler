/* This file was generated by SableCC (http://www.sablecc.org/). */

package pascal.node;

import pascal.analysis.*;

@SuppressWarnings("nls")
public final class AIfClosedElse extends PIfClosedElse
{
    private PIfStatement _ifStatement_;
    private TElse _else_;

    public AIfClosedElse()
    {
        // Constructor
    }

    public AIfClosedElse(
        @SuppressWarnings("hiding") PIfStatement _ifStatement_,
        @SuppressWarnings("hiding") TElse _else_)
    {
        // Constructor
        setIfStatement(_ifStatement_);

        setElse(_else_);

    }

    @Override
    public Object clone()
    {
        return new AIfClosedElse(
            cloneNode(this._ifStatement_),
            cloneNode(this._else_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIfClosedElse(this);
    }

    public PIfStatement getIfStatement()
    {
        return this._ifStatement_;
    }

    public void setIfStatement(PIfStatement node)
    {
        if(this._ifStatement_ != null)
        {
            this._ifStatement_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ifStatement_ = node;
    }

    public TElse getElse()
    {
        return this._else_;
    }

    public void setElse(TElse node)
    {
        if(this._else_ != null)
        {
            this._else_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._else_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ifStatement_)
            + toString(this._else_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ifStatement_ == child)
        {
            this._ifStatement_ = null;
            return;
        }

        if(this._else_ == child)
        {
            this._else_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ifStatement_ == oldChild)
        {
            setIfStatement((PIfStatement) newChild);
            return;
        }

        if(this._else_ == oldChild)
        {
            setElse((TElse) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}