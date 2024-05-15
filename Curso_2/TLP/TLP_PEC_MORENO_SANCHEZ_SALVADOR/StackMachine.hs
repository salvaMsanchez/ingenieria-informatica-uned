module StackMachine where

    -- TIPOS DE DATOS --

    type Stack a = [a]

    data BOp = ADD | SUB | MUL | DIV
        deriving (Eq, Read, Show)

    data UOp = NEG
        deriving (Eq, Read, Show)

    data SynTree a = Binary BOp (SynTree a) (SynTree a) | Unary UOp (SynTree a) | Operand a
        deriving (Eq, Read, Show)

    -- CONSTRUCCION DEL ARBOL SINTACTICO --

    createSynTree :: [String] -> SynTree Integer
    createSynTree [] = error "La lista de entrada está vacía"
    createSynTree expr = fst (createSynTree' expr)

    createSynTree' :: [String] -> (SynTree Integer, [String])
    createSynTree' [] = error "Expresión vacía"
    createSynTree' (x:xs)
        | isOperator x = case x of
            "+" -> (Binary ADD lTree rTree, rest2)
            "-" -> (Binary SUB lTree rTree, rest2)
            "*" -> (Binary MUL lTree rTree, rest2)
            "/" -> (Binary DIV lTree rTree, rest2)
            "N" -> (Unary NEG lTree, rest1)
        | otherwise    = (Operand (read x :: Integer), xs)
        where
            (lTree, rest1) = createSynTree' xs
            (rTree, rest2) = createSynTree' rest1

    isOperator :: String -> Bool
    isOperator "+" = True
    isOperator "-" = True
    isOperator "*" = True
    isOperator "/" = True
    isOperator "N" = True
    isOperator _ = False

    -- EVALUACION --

    evalSynTree :: SynTree Integer -> Integer
    evalSynTree tree = evalTree [] tree

    evalTree :: Stack Integer -> SynTree Integer -> Integer
    evalTree stack (Operand n) = n
    evalTree stack (Unary NEG expr) = - (evalTree stack expr)
    evalTree stack (Binary op l r) = case op of
        ADD -> evalTree stack' l + evalTree stack'' r
        SUB -> evalTree stack' l - evalTree stack'' r
        MUL -> evalTree stack' l * evalTree stack'' r
        DIV -> evalTree stack' l `div` evalTree stack'' r
        where
            stack' = stack
            stack'' = drop (length lStack) stack
            lStack = take (length $ stackValues l) stack
            rStack = drop (length lStack) stack

    stackValues :: SynTree Integer -> [Integer]
    stackValues (Operand n) = [n]
    stackValues (Unary _ expr) = stackValues expr
    stackValues (Binary _ l r) = stackValues l ++ stackValues r
