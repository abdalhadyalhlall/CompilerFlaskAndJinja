package ASTFlask.TheMainContintOfComplexStatment;

import ASTFlask.Statment;

import java.util.ArrayList;
import java.util.List;

public class TryStatment extends Statment {


        public List<Statment> body;
        public List<CatchStatment> handlers;
        public List<Statment> elseBody;
        public List<Statment> finallyBody;

    public TryStatment(List<Statment> body, List<CatchStatment> handlers,
                List<Statment> elseBody, List<Statment> finallyBody, int line) {
            super("TryStmt", line);
            this.body = body;
            this.handlers = handlers;
            this.elseBody = elseBody;
            this.finallyBody = finallyBody;
        }

        public void addBodyStmt(Statment stmt) {
            if (this.body == null) this.body = new ArrayList<>();
            this.body.add(stmt);
        }

        public void addHandler(CatchStatment handler) {
            if (this.handlers == null) this.handlers = new ArrayList<>();
            this.handlers.add(handler);
        }

        public void addElseStmt(Statment stmt) {
            if (this.elseBody == null) this.elseBody = new ArrayList<>();
            this.elseBody.add(stmt);
        }

        public void addFinallyStmt(Statment stmt) {
            if (this.finallyBody == null) this.finallyBody = new ArrayList<>();
            this.finallyBody.add(stmt);
        }

        @Override
        public String toString() {
            return "TryStmt(line=" + line +
                    ", body=" + body +
                    ", handlers=" + handlers +
                    ", elseBody=" + elseBody +
                    ", finallyBody=" + finallyBody + ")";
        }
}
