#include<stdio.h>
#include<locale.h>
#include"conio.c"
// Summary
//  Objective: Monitor the sales and tickets of the World Cup games stadium.
//  Entry : Name of Stadium, number of Chairs in row.
// Output  :Tickets Reserved, Purchased, Available.

void validaEstadio( char nomeEstadio[]);
float validaIngresso( float valorIngresso);
float validaPorcentagemDesconto( float desconto);
int validaQuantCadeiras( int quantCadeiras);
void validaOpcao( char *opcao);
void validaTecla( char *tecla);
void validaSexo( char *sexo);
int validaEscolha( int escolher, int cadeiras);
int validaEscolha2(int quantCadeiras, int escolher, int compradas[], int reservas[], int indice);
int validaEscolha3(int quantCadeiras, int escolher, int compradas[], int disponiveis[], int indice);
float calculoIngresso( char sexo, float descontoM, float descontoF, float valorIngresso);
void calculaIngresso( float descontoM, float descontoF, float valorIngresso);



main() {
	// Declarações
	char tecla, opcao, sexo, simbolo[150], nomeEstadio[20];
	int quantCadeiras, cadeirasEstadio[150], disponiveis[150], reservas[150], compradas[150],indice;
	int quantVendas, quantReservas, quantDisponiveis, escolher, cadeira, indiceTemp ,opcao2;
	float valorIngresso, descontoM, descontoF, totalArrecadado, preco;
	// Instruções
	quantVendas=0;
	quantReservas=0;
	quantDisponiveis=0;
	totalArrecadado=0;
	indiceTemp=0;
	indice=0;
	cadeirasEstadio[0]=100;
	
  //First information; of the Stadium, Price and discounts. 
	setlocale(LC_ALL,"portuguese");
	puts("Bem-Vindo à loja de ingressos da Copa do Mundo da Rússia!");
	puts("Qual o nome do Estádio?");
	gets(nomeEstadio);
	fflush(stdin);
	validaEstadio(nomeEstadio);
	puts("Qual o preço do ingresso normal?");
	scanf("%f",&valorIngresso);
	valorIngresso=validaIngresso(valorIngresso);
	puts("Qual o Desconto para Homens?( de 0 a 100)");
	scanf("%f",&descontoM);
	descontoM=validaPorcentagemDesconto(descontoM);
	puts("Qual o Desconto para Mulheres?(de 0 a 100)");
	scanf("%f",&descontoF);
	descontoF=validaPorcentagemDesconto(descontoF);
	puts("Aperte qualquer tecla para continuar");
	opcao = getch();
	system("CLS");
	puts("Qual a quantidade de cadeiras da fileira da tribuna");
	printf("do estádio %s?(de 51 até 149) \n", nomeEstadio);
	scanf("%i",&quantCadeiras);
	quantCadeiras=validaQuantCadeiras(quantCadeiras);
	cadeira=quantCadeiras+100;
	for(indice=0; indice<=quantCadeiras-1; indice++) {
		if(indice>0) {
			cadeirasEstadio[indice]=cadeirasEstadio[indice-1] + 1;
		}
		disponiveis[indice]=cadeirasEstadio[indice];
		simbolo[indice]='D';
		quantDisponiveis=quantDisponiveis+1;
	}

	for(indice=0; indice<=quantCadeiras-1; indice++) {
		reservas[indice]=0;
		compradas[indice]=0;
	}
// Menu, where you decide if you want to buy tickets, see tickets purchased or book.
	do {
		printf("%s \n",nomeEstadio);
		puts("O que deseja fazer?(D, C, R ou S)");
		textcolor(LIGHTBLUE);
		printf("D -");
		textcolor(WHITE);
		printf(" Ver cadeiras e as disponíveis \n");
		textcolor(RED);
		printf("C -");
		textcolor(WHITE);
		printf(" Ver vendas confirmadas \n");
		textcolor(GREEN);
		printf("R - ");
		textcolor(WHITE);
		printf("Ver reserva de cadeira \n");
		puts("S - Sair e ver resumo");
		opcao = toupper(getch());
		validaOpcao(&opcao);
		switch(opcao) {
			case 'D':
// Where you see the free chairs and where you can make your reservation
				system("CLS");
				puts("As cadeiras estão:");
				for(indice=0; indice<=quantCadeiras-1; indice++) {
					if(simbolo[indice]=='D') {
						textcolor(LIGHTBLUE);
						printf("cadeira: %i(%c) ", cadeirasEstadio[indice],simbolo[indice]);
					} else {
						if(simbolo[indice]=='C') {
							textcolor(RED);
							printf("cadeira: %i(%c) ", cadeirasEstadio[indice],simbolo[indice]);
						} else {
							if(simbolo[indice]=='R') {
								textcolor(GREEN);
								printf("cadeira: %i(%c) ", cadeirasEstadio[indice],simbolo[indice]);
							}
						}
					}
				}
				textcolor(WHITE);
				puts("");
				puts("Aperte o número correspondente à opção");
				puts("1 - Reservar cadeira.");
				puts("2 - Voltar a tela anterior");
				scanf("%i",&opcao2);
				while(opcao2!=1 && opcao2!=2) {
					system("CLS");
					puts("");
					puts("Aperte o número correspondente à opção");
					puts("1 - Reservar cadeira.");
					puts("2 - Voltar a tela anterior");
					puts("Opção inválida! Tente novamente!");
					scanf("%i",&opcao2);
				}
				if(opcao2==2) {
					system("CLS");
					break;
				} else {
					system("CLS");
					for(indice=0; indice<=quantCadeiras-1; indice++) {
						if(disponiveis[indice]>=100 && simbolo[indice]=='D') {
							textcolor(LIGHTBLUE);
							printf("cadeira: %i(%c) ", disponiveis[indice],simbolo[indice]);
						}
						textcolor(WHITE);
					}
					puts("");
					puts("Qual cadeira deseja reservar?");
					scanf("%i",&escolher);
					escolher=validaEscolha(escolher, cadeira);
					escolher=validaEscolha2(quantCadeiras, escolher, compradas, reservas, indice);
					for(indice=0; indice<=quantCadeiras-1; indice++) {
						if(escolher==disponiveis[indice]) {
							disponiveis[indice]=0;
							reservas[indice]=escolher;
							simbolo[indice]='R';
							indiceTemp=indice;
							quantDisponiveis=quantDisponiveis-1;
							quantReservas=quantReservas+1;
						}
					}
					puts("Armazenado a Reserva com Sucesso! Aperte qualquer tecla para continuar");
					opcao = getch();
					system("CLS");
					break;
				}
			case 'C':
      //Where you see the chairs already reserved.
				system("CLS");
				puts("Os ingressos das cadeiras compradas são:");
				for(indice=0; indice<=quantCadeiras-1; indice++) {
					if(compradas[indice]>0 && simbolo[indice]=='C') {
						textcolor(RED);
						printf("cadeiras: %i(%c) \n", cadeirasEstadio[indice],simbolo[indice]);
					}
					textcolor(WHITE);
				}
				puts("Aperte qualquer tecla para continuar");
				tecla = getch();
				system("CLS");
				break;

			case 'R':
      //Where you see your reservation and where you can buy your ticket.
				system("CLS");
				puts("As cadeiras Reservadas são:");
				for(indice=0; indice<=quantCadeiras-1; indice++) {
					if(reservas[indice]>=0 && simbolo[indice]=='R') {
						textcolor(GREEN);
						printf("cadeira: %i(%c) \n", cadeirasEstadio[indice],simbolo[indice]);
					}
				}
				textcolor(WHITE);
				puts("");
				puts("Aperte o número correspondente à opção");
				puts("1 - Cancelar pedido.");
				puts("2 - Confirmar compra.");
				puts("3 - voltar ao menu.");
				scanf("%i",&opcao2);
				while(opcao2!=1 && opcao2!=2 && opcao2!=3) {
					system("CLS");
					puts("");
					puts("Aperte o número correspondente à opção");
					puts("1 - Cancelar pedido.");
					puts("2 - Confirmar compra.");
					puts("3 - voltar ao menu.");
					puts("Opção inválida! Tente novamente!");
					scanf("%i",&opcao2);
				}
				system("CLS");
				if(quantReservas>0) {
					if(opcao2==3) {
						break;
					} else {
						if(opcao2==1) {
							system("CLS");
							puts("As cadeiras Reservadas são:");
							for(indice=0; indice<=quantCadeiras-1; indice++) {
								if(reservas[indice]>0 && simbolo[indice]=='R') {
									textcolor(GREEN);
									printf("cadeira: %i(%c) \n", cadeirasEstadio[indice],simbolo[indice]);
								}
							}
							textcolor(WHITE);
							puts("Qual pedido deseja cancelar?(digite o número da cadeira)");
							scanf("%i",&escolher);
							escolher=validaEscolha(escolher, cadeira);
							escolher=validaEscolha3( quantCadeiras, escolher, compradas, disponiveis, indice);
							escolher=validaEscolha(escolher, cadeira);
							for(indice=0; indice<=quantCadeiras-1; indice++) {
								if(escolher==reservas[indice]) {
									reservas[indice]=0;
									disponiveis[indice]=escolher;
									simbolo[indice]='D';
									quantReservas=quantReservas-1;
									quantDisponiveis=quantDisponiveis+1;
								}
							}
							puts("Reserva Cancelada com sucesso! Aperte qualquer tecla para continuar");
							tecla = getch();
							system("CLS");
							break;
						} else {
							if(opcao2==2) {
								for(indice=0; indice<=quantCadeiras-1; indice++) {
									if(reservas[indice]>0) {
										textcolor(GREEN);
										printf("cadeira: %i(%c) \n", cadeirasEstadio[indice],simbolo[indice]);
									}
								}
								textcolor(WHITE);
								puts("");
								puts("Qual pedido deseja confirmar?(digite o número da cadeira)");
								scanf("%i",&escolher);
								escolher=validaEscolha(escolher, cadeira);
								escolher=validaEscolha3( quantCadeiras, escolher, compradas, disponiveis, indice);
								escolher=validaEscolha(escolher, cadeira);
								system("CLS");
								calculaIngresso( descontoM, descontoF, valorIngresso);
								puts("Deseja comprar o ingresso?(S ou N)");
								tecla = toupper(getch());
								validaTecla(&tecla);
								if(tecla=='S') {
									puts("Qual seu sexo?(M ou F)");
									sexo = toupper(getch());
								
									validaSexo(&sexo);
									system("CLS");
									preco=calculoIngresso(sexo, descontoM, descontoF, valorIngresso);
									for(indice=0; indice<=quantCadeiras-1; indice++) {
										if(escolher==reservas[indice]) {
											reservas[indice]=0;
											compradas[indice]=escolher;
											simbolo[indice]='C';
											quantReservas=quantReservas-1;
											quantVendas=quantVendas+1;
											totalArrecadado=totalArrecadado+preco;
										}
									}
										puts("Compra feita com sucesso! Aperte qualquer tecla para continuar");
									tecla = getch();
									system("CLS");
								} else {
									system("CLS");
								}
								break;
							} else {
								puts("Aperte qualquer tecla para continuar");
								tecla = getch();
								system("CLS");
								break;
							}
						}
					}
				} else {
					puts("Não existem reservas! Aperte qualquer tecla para voltar ao menu.");
					tecla = getch();
					system("CLS");
					break;
				}
			case'S':
				break;
		}
	} while(opcao!='S');
  //The summary of all the money collected, all the seats bought, reserved and free. 
	textcolor(MAGENTA);
	printf("O valor total acumulado nas vendas foi:  %.1f \n", totalArrecadado, " reais");
	puts("");
	textcolor(RED);
	printf("O total de vendas confirmadas é:  %i \n ", quantVendas);
	puts("");
	textcolor(GREEN);
	printf("O total de cadeiras reservadas é: %i \n", quantReservas);
	puts("");
	textcolor(LIGHTBLUE);
	printf("O total de cadeiras disponíveis é:  %i \n ", quantDisponiveis);

}

// Summary
// Objective: Validate the name of the Stadium.
// Entry: Name of the Stadium.
// Output: Name of the Stadium.
void validaEstadio( char nomeEstadio[]) {
	while(strcmp(nomeEstadio,"")==0) {
		system("CLS");
		puts("Nome do Estádio inválido, tente novamente!");
		gets(nomeEstadio);
		fflush(stdin);
	}
	system("CLS");
}

// Summary
// Objective: Validate the ticket price.
// Entry: Ticket price.
// Output: Ticket price.
float validaIngresso( float valorIngresso) {
	while(valorIngresso<=0) {
		system("CLS");
		puts("O preço do ingresso é inválido! Tente Novamente!");
		scanf("%f",&valorIngresso);
	}
	system("CLS");
	return valorIngresso;
}

// Summary
// Purpose: Validate the discount percentage.
// Input: Percentage.
// Output: Percentage.
float validaPorcentagemDesconto( float desconto) {
	while(desconto>100 || desconto<0) {
		system("CLS");
		puts("Desconto inválido! Tente novamente!");
		scanf("%f",&desconto);
	}
	system("CLS");
	return desconto;
}

// Summary
// Objective: Validate the number of seats.
// Entry: Number of seats.
// Output: Number of seats.
int validaQuantCadeiras( int quantCadeiras) {
	while(quantCadeiras<=50 || quantCadeiras>=150) {
		system("CLS");
		puts("Quantidade de cadeiras inválidas!(de 51 até 149)");
		scanf("%i",&quantCadeiras);
	}
	system("CLS");
	return quantCadeiras;
}

// Summary
// Purpose: Validate the store option.
// Input: Option.
// Output: Option.
void validaOpcao( char *opcao) {
	while(*opcao!='D' && *opcao!='C' && *opcao!='R' && *opcao!='S') {
		system("CLS");
		puts("O que deseja fazer?(D, C, R ou S)");
		puts("D - Ver cadeiras disponíveis");
		puts("C - Ver vendas confirmadas");
		puts("R - Ver reserva de cadeira");
		puts("S - Sair e ver resumo");
		puts("");
		puts("Opção inválida! Tente novamente!");
		*opcao = toupper(getch());
		
	}
	system("CLS");
}

// Summary
// Purpose: Validate the choice (S or N; Yes or No).
// Input: Choose.
// Output: Choose.
void validaTecla( char *tecla) {
	while(*tecla!='S' && *tecla!='N') {
		puts("Opção inválida! Tente Novamente(S ou N)!");
		*tecla = toupper(getch());
	
	}
}

// Summary
// Objective: Validate the Sex.
// Input: Sex.
// Output: Sex.
void validaSexo(char *sexo) {
	while(*sexo!='M' && *sexo!='F') {
		system("CLS");
		puts("Sexo inválido! Tente novamente(M ou F)!");
		*sexo = toupper(getch());
	
	system("CLS");
}
}

// Summary
// Objective: Validate the existing chair.
// Entrance: Chair.
// Output: Chair.
int validaEscolha( int escolher, int cadeiras) {
	while(escolher>cadeiras-1 || escolher<=50) {
		puts("Cadeira inválida! Tente Novamente!");
		scanf("%i",&escolher);
	}
	return escolher;
}

// Summary
// Objective: Check if the chair has already been bought or reserved.
// Entrance: Chair.
// Output: Chair.
int validaEscolha2(int quantCadeiras, int escolher, int compradas[], int reservas[], int indice) {
	int indice2;
	indice2=0;
	for(indice=0; indice<=quantCadeiras-1; indice++) {
		for(indice2=0; indice2<=quantCadeiras-1; indice2++) {
			if(indice!=indice2) {
				while(escolher==compradas[indice2]) {
					puts("Cadeira já comprada! Tente novamente!");
					scanf("%i",&escolher);
				}
				while(escolher==reservas[indice2]) {
					puts("Cadeira já Reservada! Tente novamente!");
					scanf("%i",&escolher);
				}
			}
		}
	}
	return escolher;
}

// Summary
// Objective: Check if the chair has already been bought or available.
// Entrance: Chair.
// Output: Chair.
int validaEscolha3(int quantCadeiras, int escolher, int compradas[], int disponiveis[], int indice) {
	int indice2;
	indice2=0;
	for(indice=0; indice<=quantCadeiras-1; indice++) {
		for(indice2=0; indice2<=quantCadeiras-1; indice2++) {
			if(indice!=indice2) {
				while(escolher==compradas[indice2]) {
					puts("Cadeira já comprada! Tente novamente!");
					scanf("%i",&escolher);
				}
				while(escolher==disponiveis[indice2]) {
					puts("Cadeira não Reservada! Tente novamente!");
					scanf("%i",&escolher);
				}
			}
		}
	}
	return escolher;
}

// Summary
// Objective: Calculate the price of the ticket.
// Entry: Price, Male and Female discount.
// Exit: New ticket price.
float calculoIngresso( char sexo, float descontoM, float descontoF, float valorIngresso) {
	float ingresso, desconto;
	ingresso=0;
	desconto=0;
	if(sexo=='M') {
		desconto=(descontoM*valorIngresso)/100;
		ingresso=valorIngresso-desconto;
		printf("%.1f - %.1f = %.1f reais \n",valorIngresso, desconto, ingresso);
	} else {
		desconto=(descontoF*valorIngresso)/100;
		ingresso=valorIngresso-desconto;
		printf("%.1f - %.1f = %.1f reais \n",valorIngresso, desconto, ingresso);
	}
	return ingresso;
}

// Summary
// Objective: To present the calculation of the ticket.
// Entry: Ticket price, Male and Female discount.
// Output: New Price and its calculation.
void calculaIngresso( float descontoM, float descontoF, float valorIngresso) {

	float ingressoM, ingressoF, descontosM, descontosF;
	ingressoM=0;
	ingressoF=0;
	descontosF=0;
	descontosM=0;
	descontosM=(descontoM*valorIngresso)/100;
	ingressoM=valorIngresso-descontosM;
	descontosF=(descontoF*valorIngresso)/100;
	ingressoF=valorIngresso-descontosF;
	puts("Ingresso para homens:");
	printf("%.1f - %.1f = %.1f reais \n",valorIngresso, descontoM, ingressoM);
	puts("Ingresso para Mulheres:");
	printf("%.1f - %.1f = %.1f reais \n",valorIngresso, descontoF, ingressoF);
}

