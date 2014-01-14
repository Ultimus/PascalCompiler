/* This file was generated by SableCC (http://www.sablecc.org/). */

package pascal.analysis;

import java.util.*;
import pascal.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAProgramStartAst(AProgramStartAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAProgramHeaderAst(AProgramHeaderAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADeclarationsAst(ADeclarationsAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEmptyDeclarationsAst(AEmptyDeclarationsAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVariablesDefinitionListAst(AVariablesDefinitionListAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVariablesDefinitionAst(AVariablesDefinitionAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIntegerAst(AIntegerAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABooleanAst(ABooleanAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABodyAst(ABodyAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAStatementSequenceAst(AStatementSequenceAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAStatementAst(AStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOpenStatementAst(AOpenStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAClosedStatementAst(AClosedStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOpenIfStatementAst(AOpenIfStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOpenIfElseStatementAst(AOpenIfElseStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAClosedIfStatementAst(AClosedIfStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOpenWhileStatementAst(AOpenWhileStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAClosedWhileStatementAst(AClosedWhileStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleStatementAst(ASimpleStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAWritelnAst(AWritelnAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAssignmentAst(AAssignmentAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABlockAst(ABlockAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEmptyAst(AEmptyAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIfStatementAst(AIfStatementAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEqualAst(AEqualAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALessAst(ALessAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGreaterAst(AGreaterAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAGreaterEqAst(AGreaterEqAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALessEqAst(ALessEqAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAUnequalAst(AUnequalAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExprTopAst(AExprTopAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOrAst(AOrAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAXorAst(AXorAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPlusAst(APlusAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMinusAst(AMinusAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAndAst(AAndAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAModAst(AModAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultAst(AMultAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivAst(ADivAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANotAst(ANotAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANumberAst(ANumberAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIdentifierAst(AIdentifierAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATrueAst(ATrueAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFalseAst(AFalseAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAUnMinusAst(AUnMinusAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAUnPlusAst(AUnPlusAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABreakAst(ABreakAst node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEnd(TEnd node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVar(TVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBegin(TBegin node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTProgram(TProgram node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTWriteln(TWriteln node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTInteger(TInteger node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBoolean(TBoolean node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTrue(TTrue node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFalse(TFalse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIf(TIf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTWhile(TWhile node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTBreak(TBreak node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDo(TDo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTThen(TThen node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTElse(TElse node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAnd(TAnd node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTOr(TOr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTXor(TXor node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNot(TNot node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMod(TMod node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAssign(TAssign node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEqual(TEqual node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLess(TLess node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGreater(TGreater node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLessEq(TLessEq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTGreaterEq(TGreaterEq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTUnequal(TUnequal node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSemicolon(TSemicolon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDot(TDot node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLPar(TLPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRPar(TRPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIdentifier(TIdentifier node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTComment(TComment node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTWhitespace(TWhitespace node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}