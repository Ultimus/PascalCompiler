/* This file was generated by SableCC (http://www.sablecc.org/). */

package pascal.node;

import pascal.analysis.*;

@SuppressWarnings("nls")
public final class ABlockSimpleStatement extends PSimpleStatement
{
    private TBegin _begin_;
    private PStatementSequence _statementSequence_;
    private TEnd _end_;

    public ABlockSimpleStatement()
    {
        // Constructor
    }

    public ABlockSimpleStatement(
        @SuppressWarnings("hiding") TBegin _begin_,
        @SuppressWarnings("hiding") PStatementSequence _statementSequence_,
        @SuppressWarnings("hiding") TEnd _end_)
    {
        // Constructor
        setBegin(_begin_);

        setStatementSequence(_statementSequence_);

        setEnd(_end_);

    }

    @Override
    public Object clone()
    {
        return new ABlockSimpleStatement(
            cloneNode(this._begin_),
            cloneNode(this._statementSequence_),
            cloneNode(this._end_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABlockSimpleStatement(this);
    }

    public TBegin getBegin()
    {
        return this._begin_;
    }

    public void setBegin(TBegin node)
    {
        if(this._begin_ != null)
        {
            this._begin_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._begin_ = node;
    }

    public PStatementSequence getStatementSequence()
    {
        return this._statementSequence_;
    }

    public void setStatementSequence(PStatementSequence node)
    {
        if(this._statementSequence_ != null)
        {
            this._statementSequence_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._statementSequence_ = node;
    }

    public TEnd getEnd()
    {
        return this._end_;
    }

    public void setEnd(TEnd node)
    {
        if(this._end_ != null)
        {
            this._end_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._end_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._begin_)
            + toString(this._statementSequence_)
            + toString(this._end_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._begin_ == child)
        {
            this._begin_ = null;
            return;
        }

        if(this._statementSequence_ == child)
        {
            this._statementSequence_ = null;
            return;
        }

        if(this._end_ == child)
        {
            this._end_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._begin_ == oldChild)
        {
            setBegin((TBegin) newChild);
            return;
        }

        if(this._statementSequence_ == oldChild)
        {
            setStatementSequence((PStatementSequence) newChild);
            return;
        }

        if(this._end_ == oldChild)
        {
            setEnd((TEnd) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
