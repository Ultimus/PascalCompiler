/* This file was generated by SableCC (http://www.sablecc.org/). */

package pascal.node;

import java.util.*;
import pascal.analysis.*;

@SuppressWarnings("nls")
public final class AProgramStartAst extends PAst
{
    private PAst _left_;
    private final LinkedList<PAst> _m_ = new LinkedList<PAst>();
    private PAst _right_;

    public AProgramStartAst()
    {
        // Constructor
    }

    public AProgramStartAst(
        @SuppressWarnings("hiding") PAst _left_,
        @SuppressWarnings("hiding") List<?> _m_,
        @SuppressWarnings("hiding") PAst _right_)
    {
        // Constructor
        setLeft(_left_);

        setM(_m_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AProgramStartAst(
            cloneNode(this._left_),
            cloneList(this._m_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAProgramStartAst(this);
    }

    public PAst getLeft()
    {
        return this._left_;
    }

    public void setLeft(PAst node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public LinkedList<PAst> getM()
    {
        return this._m_;
    }

    public void setM(List<?> list)
    {
        for(PAst e : this._m_)
        {
            e.parent(null);
        }
        this._m_.clear();

        for(Object obj_e : list)
        {
            PAst e = (PAst) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._m_.add(e);
        }
    }

    public PAst getRight()
    {
        return this._right_;
    }

    public void setRight(PAst node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._m_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._m_.remove(child))
        {
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PAst) newChild);
            return;
        }

        for(ListIterator<PAst> i = this._m_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PAst) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._right_ == oldChild)
        {
            setRight((PAst) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}