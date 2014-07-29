Campaign Revenue Solvre
=================

This project tries to solve a java puzzle which involves in using the unbounded knapsack algorithm to choose how many campaigns to sell to what customers in order to maximize the revenue.

In this case the items of the knapsack resemble campaigns with the customer name, impressions per campaign and value per campaign.

The capacity of the knapsack will be the total impressions for the month given for the problem.

To run, run the campaignOptimizer main class with the filepath to the file containing the data of the problem, following the structure:

monthly inventory

customer,impressions per campaign,price per campaign

... 

customer,impressions per campaign,price per campaign

It counts with optimizations:

-First we order the items by the ratio Impression/value.

-Apply dominance relations which remove infeasible items which are dominated. Implemented by Martello's and Toth's algorithm.

When finished the problem will print the solution in the following format

customer,number of campaigns to sell,total impressions for customer,total revenue for customer

...

total number of impressions,total revenue

References:

-http://en.wikipedia.org/wiki/Knapsack_problem#Unbounded_knapsack_problem

-S. Martello, P. Toth, Knapsack Problems: Algorithms and Computer Implementation, John Wiley and Sons, 1990
